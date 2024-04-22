import { cn } from '@/util/utils';
import React from 'react';

interface SectionMenuProps {
  title: string;
  className?: string;
  children: React.ReactNode;
}

const SectionMenu = ({ title, className, children }: SectionMenuProps) => {
  return (
    <section className={cn('mb-10', className)}>
      <h2
        className={
          'text-primary-blue text-xl font-semibold p-2 border-b-[1px] border-primary-blue mb-3'
        }
      >
        {title}
      </h2>
      {children}
    </section>
  );
};

export default SectionMenu;
