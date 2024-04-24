'use client';

import React, { useEffect, useState } from 'react';
import { Dialog, Flex, Button } from '@radix-ui/themes';
import {
  useForm,
  type SubmitHandler,
  type Control,
  type FieldErrors,
} from 'react-hook-form';
import { Club, type RequestData } from '@/types/apiResponse';
import { Spinner } from '@radix-ui/themes';
import CalloutUi from '@/components/ui/CalloutUi';
import { UseMutationResult } from '@tanstack/react-query';

interface DialogProps {
  initialValue: RequestData;
  mutation: UseMutationResult<Club, Error, unknown, unknown>;
  children: (
    control: Control<RequestData, any>,
    errors: FieldErrors<RequestData>,
  ) => React.ReactNode;
}

const Form = ({ initialValue, mutation, children }: DialogProps) => {
  const [isSuccess, setIsSuccess] = useState<boolean>(false);
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<RequestData>({
    defaultValues: { ...initialValue },
  });

  const onSubmit: SubmitHandler<RequestData> = (data) => {
    console.log(data);
    mutation.mutate(data, {
      onSuccess: () => setIsSuccess(true),
    });
  };

  useEffect(() => {
    setIsSuccess(false);
    mutation.reset();
  }, []);

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Flex direction="column" gap="3">
        {children(control, errors)}
      </Flex>

      {mutation.isPending && <Spinner className="mx-auto my-2" size="3" />}
      {mutation.error && <CalloutUi message={mutation.error.message} />}
      {isSuccess && <CalloutUi message="Success!" />}

      <Flex gap="3" mt="4" justify="end">
        {isSuccess ? (
          <Dialog.Close>
            <Button>Close</Button>
          </Dialog.Close>
        ) : (
          <>
            <Dialog.Close>
              <Button variant="soft" color="gray">
                Cancel
              </Button>
            </Dialog.Close>
            <Button type="submit" className="bg-primary-blue">
              Save
            </Button>
          </>
        )}
      </Flex>
    </form>
  );
};

export default Form;
