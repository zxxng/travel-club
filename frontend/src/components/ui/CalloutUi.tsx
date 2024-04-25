import React from 'react';
import { Callout } from '@radix-ui/themes';
import { Info } from 'lucide-react';
import { cn } from '@/src/util/utils';

interface CalloutUiProps {
  message: string;
  className?: string;
}

const CalloutUi = ({ message, className }: CalloutUiProps) => {
  return (
    <Callout.Root className={cn('my-3', className)}>
      <Callout.Icon>
        <Info />
      </Callout.Icon>
      <Callout.Text>{message}</Callout.Text>
    </Callout.Root>
  );
};

export default CalloutUi;
