import React, { useEffect, useState } from 'react';
import { AlertDialog, Button, Flex } from '@radix-ui/themes';
import { Spinner } from '@radix-ui/themes';
import CalloutUi from '@/components/ui/CalloutUi';
import { UseMutationResult } from '@tanstack/react-query';
import { Club } from '@/types/apiResponse';

interface AlertDialogUiProps {
  message: string;
  mutation: UseMutationResult<Club, Error, unknown, unknown>;
}

const AlertDialogUi = ({ message, mutation }: AlertDialogUiProps) => {
  const [isSuccess, setIsSuccess] = useState<boolean>(false);

  const handleRemove = () => {
    mutation.mutate({ onSuccess: () => setIsSuccess(true) });
  };
  useEffect(() => {
    setIsSuccess(false);
    mutation.reset();
  }, []);

  return (
    <AlertDialog.Root>
      <AlertDialog.Trigger>
        <Button color="red">Remove</Button>
      </AlertDialog.Trigger>
      <AlertDialog.Content maxWidth="450px">
        <AlertDialog.Title>Remove</AlertDialog.Title>
        <AlertDialog.Description size="2">{message}</AlertDialog.Description>

        {mutation.isPending && <Spinner className="mx-auto my-2" size="3" />}
        {mutation.error && <CalloutUi message={mutation.error.message} />}
        {isSuccess && <CalloutUi message="Success!" />}

        <Flex gap="3" mt="4" justify="end">
          {isSuccess ? (
            <AlertDialog.Cancel>
              <Button variant="soft" color="red">
                Close
              </Button>
            </AlertDialog.Cancel>
          ) : (
            <>
              <AlertDialog.Cancel>
                <Button variant="soft" color="gray">
                  Cancel
                </Button>
              </AlertDialog.Cancel>
              <Button
                type="button"
                variant="solid"
                color="red"
                onClick={handleRemove}
              >
                Remove
              </Button>
            </>
          )}
        </Flex>
      </AlertDialog.Content>
    </AlertDialog.Root>
  );
};

export default AlertDialogUi;
