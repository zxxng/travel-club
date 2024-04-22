import { cn } from '@/util/utils';
import React from 'react';

interface SectionMenuProps {
  title: string;
  className?: string;
  children: React.ReactNode;
}

const SectionMenu = ({ title, className, children }: SectionMenuProps) => {
  return (
    <section className="mb-10">
      <h2
        className={cn(
          'text-primary-blue text-xl font-semibold p-2 border-b-2 border-primary-blue mb-2',
          className,
        )}
      >
        {title}
      </h2>
      {children}
    </section>
  );
};

export default SectionMenu;
