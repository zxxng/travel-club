import React from 'react';
import { Callout } from '@radix-ui/themes';
import { Info } from 'lucide-react';

interface CalloutUiProps {
  message: string;
}

const CalloutUi = ({ message }: CalloutUiProps) => {
  return (
    <Callout.Root className="my-3">
      <Callout.Icon>
        <Info />
      </Callout.Icon>
      <Callout.Text>{message}</Callout.Text>
    </Callout.Root>
  );
};

export default CalloutUi;
