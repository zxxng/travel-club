import React from 'react';
import { AlertDialog, Button, Flex } from '@radix-ui/themes';

interface AlertDialogUiProps {
  message: string;
  children: React.ReactNode;
  handleRemove: () => void;
}

const AlertDialogUi = ({
  message,
  children,
  handleRemove,
}: AlertDialogUiProps) => {
  return (
    <AlertDialog.Root>
      <AlertDialog.Trigger>
        <Button color="red">Remove</Button>
      </AlertDialog.Trigger>
      <AlertDialog.Content maxWidth="450px">
        <AlertDialog.Title>Remove</AlertDialog.Title>
        <AlertDialog.Description size="2">{message}</AlertDialog.Description>

        {children}

        <Flex gap="3" mt="4" justify="end">
          <AlertDialog.Cancel>
            <Button variant="soft" color="gray">
              Cancel
            </Button>
          </AlertDialog.Cancel>
          <AlertDialog.Action>
            <Button variant="solid" color="red" onClick={handleRemove}>
              Remove
            </Button>
          </AlertDialog.Action>
        </Flex>
      </AlertDialog.Content>
    </AlertDialog.Root>
  );
};

export default AlertDialogUi;
