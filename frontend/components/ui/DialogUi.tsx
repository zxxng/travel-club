import React from 'react';
import { Dialog, Flex, Button } from '@radix-ui/themes';

interface DialogProps {
  btnText: string;
  children: React.ReactNode;
  onClick: () => void;
}

const DialogUi = ({ btnText, children, onClick }: DialogProps) => {
  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <Button>{btnText}</Button>
      </Dialog.Trigger>

      <Dialog.Content maxWidth="450px">
        {children}

        <Flex gap="3" mt="4" justify="end">
          <Dialog.Close>
            <Button variant="soft" color="gray">
              Cancel
            </Button>
          </Dialog.Close>
          <Dialog.Close>
            <Button className="bg-primary-blue" onClick={onClick}>
              Save
            </Button>
          </Dialog.Close>
        </Flex>
      </Dialog.Content>
    </Dialog.Root>
  );
};

export default DialogUi;
