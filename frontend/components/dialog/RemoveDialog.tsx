'use client';

import React, { useEffect } from 'react';
import { AlertDialog, Button, Flex } from '@radix-ui/themes';
import { Spinner } from '@radix-ui/themes';
import CalloutUi from '@/components/ui/CalloutUi';
import { UseMutationResult, useQueryClient } from '@tanstack/react-query';
import { Club } from '@/types/apiResponse';

interface AlertDialogUiProps {
  message: string;
  mutation: UseMutationResult<Club, Error, unknown, unknown>;
}

const RemoveDialog = ({ message, mutation }: AlertDialogUiProps) => {
  const queryClient = useQueryClient();
  const handleRemove = () => {
    mutation.mutate('', {
      onSuccess: () => queryClient.invalidateQueries({ queryKey: ['get'] }),
    });
  };

  useEffect(() => {
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
        {mutation.isSuccess && <CalloutUi message="Deletion successful" />}

        <Flex gap="3" mt="4" justify="end">
          {mutation.isSuccess ? (
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

export default RemoveDialog;
