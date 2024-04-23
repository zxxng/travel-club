'use client';

import React from 'react';
import { Dialog, Flex, Button } from '@radix-ui/themes';
import { useForm, type SubmitHandler } from 'react-hook-form';
import Input from '../common/Input';
import { type RequestData } from '@/types/apiResponse';

interface DialogProps {
  text: { title: string; btnType: string };
  initialValue: RequestData;
  isSuccess: boolean;
  children: React.ReactNode;
  handleRequest: (data: RequestData) => void;
}

const DialogUi = ({
  text,
  initialValue,
  isSuccess,
  children,
  handleRequest,
}: DialogProps) => {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<RequestData>({
    defaultValues: { ...initialValue },
  });

  const onSubmit: SubmitHandler<RequestData> = (data) => {
    console.log(data);
    handleRequest(data);
  };

  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <Button>{text.btnType}</Button>
      </Dialog.Trigger>

      <Dialog.Content maxWidth="450px">
        <Dialog.Title>{text.title}</Dialog.Title>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Flex direction="column" gap="3">
            <Input.Name control={control} errors={errors} />
            <Input.Intro control={control} errors={errors} />
          </Flex>

          {children}

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
      </Dialog.Content>
    </Dialog.Root>
  );
};

export default DialogUi;
