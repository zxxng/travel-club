'use client';

import React from 'react';
import { Dialog, Button } from '@radix-ui/themes';

interface CustomDialogProps {
  text: { title: string; btnType: string };
  children: React.ReactNode;
}

const CustomDialog = ({ text, children }: CustomDialogProps) => {
  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <Button>{text.btnType}</Button>
      </Dialog.Trigger>

      <Dialog.Content maxWidth="450px">
        <Dialog.Title>{text.title}</Dialog.Title>
        {children}
      </Dialog.Content>
    </Dialog.Root>
  );
};

export default CustomDialog;
