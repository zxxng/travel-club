'use client';

import React, { useState } from 'react';
import DialogUi from '../ui/DialogUi';
import AlertDialogUi from '../ui/AlertDialogUi';
import { Spinner } from '@radix-ui/themes';
import useApiMutation from '@/hooks/useApiMutation';
import CalloutUi from '../ui/CalloutUi';
import { type Club, type RequestData } from '@/types/apiResponse';

interface ClubDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  clubData?: Club;
}

const ClubDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  clubData,
}: ClubDialogProps) => {
  const [isSuccess, setIsSuccess] = useState<boolean>(false);
  const { mutate, error, isPending } = useApiMutation<Club>(url, method, {
    onSuccess: (data) => {
      console.log(`${dialogTitle} successfully:`, data);
      setIsSuccess(true);
    },
    onError: (error) => {
      console.error(`Error during ${dialogTitle.toLowerCase()}:`, error);
    },
    retry: 1,
  });

  const handleRemove = () => {
    mutate({});
  };

  if (method === 'DELETE' && clubData)
    return (
      <AlertDialogUi
        message={`Removing club -> [id] ${clubData.id}, [name] ${clubData.name}`}
        handleRemove={handleRemove}
      >
        {isPending && <Spinner className="mx-auto" size="3" />}
        {error && <CalloutUi message={error.message} />}
        {isSuccess && <CalloutUi message="Success!" />}
      </AlertDialogUi>
    );

  const handleRequest = (data: RequestData) => {
    mutate(data);
  };

  return (
    <DialogUi
      text={{
        title: dialogTitle,
        btnType: buttonText,
      }}
      isSuccess={isSuccess}
      initialValue={initialValues}
      handleRequest={handleRequest}
    >
      {isPending && <Spinner className="mx-auto" size="3" />}
      {error && <CalloutUi message={error.message} />}
      {isSuccess && <CalloutUi message="Success!" />}
    </DialogUi>
  );
};

const ClubRegistDialog = () => {
  return (
    <ClubDialog
      url="/club"
      method="POST"
      initialValues={{ name: '', intro: '' }}
      dialogTitle="Club Registration"
      buttonText="Register"
    />
  );
};

const ClubModifyDialog = ({
  id,
  name,
  intro,
}: {
  id: number;
  name: string;
  intro: string;
}) => {
  return (
    <ClubDialog
      url="/club"
      method="PUT"
      initialValues={{ id, name, intro }}
      dialogTitle="Club Modification"
      buttonText="Modify"
    />
  );
};

const ClubRemoveDialog = ({ clubData }: { clubData: Club }) => {
  return (
    <ClubDialog
      url={`/club/${clubData.id}`}
      method="DELETE"
      initialValues={{}}
      dialogTitle="Club Removing"
      buttonText="Remove"
      clubData={clubData}
    />
  );
};

ClubDialog.Regist = ClubRegistDialog;
ClubDialog.Modify = ClubModifyDialog;
ClubDialog.Delete = ClubRemoveDialog;

export default ClubDialog;
