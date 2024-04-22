'use client';

import React, { useRef } from 'react';
import SectionMenu from './SectionMenu';
import { TextField, IconButton } from '@radix-ui/themes';
import { Search } from 'lucide-react';
import { usePathname } from 'next/navigation';
import { useRouter } from 'next/navigation';

interface SearchBarProps {
  title: string;
  placeholder: string;
}

const SearchBar = ({ title, placeholder }: SearchBarProps) => {
  const keywordRef = useRef<HTMLInputElement>(null);
  const pathName = usePathname();
  const router = useRouter();

  const handleKeyword = () => {
    const keword = keywordRef.current?.value;
    const baseUrl = pathName.split('?')[0];

    if (keword) {
      router.push(`${baseUrl}?keyword=${keywordRef.current?.value}`);
    } else {
      router.push(baseUrl);
    }
  };

  return (
    <SectionMenu title={title}>
      <div className="flex gap-1">
        <TextField.Root
          ref={keywordRef}
          placeholder={placeholder}
          className="w-96 h-12 border-primary-blue border-2"
          onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => {
            if (e.key === 'Enter') handleKeyword();
          }}
        >
          <TextField.Slot>
            <Search height="16" width="16" />
          </TextField.Slot>
        </TextField.Root>
        <IconButton onClick={handleKeyword}>
          <Search width="18" height="18" />
        </IconButton>
      </div>
    </SectionMenu>
  );
};

export default SearchBar;
