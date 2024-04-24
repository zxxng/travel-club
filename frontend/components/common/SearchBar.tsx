'use client';

import React, { useRef } from 'react';
import { TextField, IconButton } from '@radix-ui/themes';
import { Search } from 'lucide-react';
import SelectUi from '../ui/SelectUi';
import { useSetAtom } from 'jotai';
import { keywordAtom } from '@/atom/searchAtom';

const SearchBar = () => {
  const setKeyword = useSetAtom(keywordAtom);
  const keywordRef = useRef<HTMLInputElement>(null);

  const handleKeyword = () => {
    setKeyword(keywordRef.current?.value || '');
  };

  return (
    <div className="flex gap-2">
      <SelectUi list={['ID', 'Name']} />
      <TextField.Root
        ref={keywordRef}
        placeholder="Please enter keyword..."
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
  );
};

export default SearchBar;
