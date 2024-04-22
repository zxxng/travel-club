import React from 'react';
import { AlertDialog, Button, Flex } from '@radix-ui/themes';

interface AlertDialogUiProps {
  message: string;
  onClick: () => void;
}

const AlertDialogUi = ({ message, onClick }: AlertDialogUiProps) => {
  return (
    <AlertDialog.Root>
      <AlertDialog.Trigger>
        <Button color="red">Remove</Button>
      </AlertDialog.Trigger>
      <AlertDialog.Content maxWidth="450px">
        <AlertDialog.Title>Rmove</AlertDialog.Title>
        <AlertDialog.Description size="2">{message}</AlertDialog.Description>

        <Flex gap="3" mt="4" justify="end">
          <AlertDialog.Cancel>
            <Button variant="soft" color="gray">
              Cancel
            </Button>
          </AlertDialog.Cancel>
          <AlertDialog.Action>
            <Button variant="solid" color="red" onClick={onClick}>
              Remove
            </Button>
          </AlertDialog.Action>
        </Flex>
      </AlertDialog.Content>
    </AlertDialog.Root>
  );
};

export default AlertDialogUi;
