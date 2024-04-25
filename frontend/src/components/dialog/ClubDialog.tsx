'use client';

import React from 'react';
import useApiMutation from '@/src/hooks/useApiMutation';
import { type Club, type RequestData } from '@/src/types/apiResponse';
import RemoveDialog from './common/RemoveDialog';
import CustomDialog from './common/CustomDialog';
import Form from '@/src/components/dialog/common/Form';
import Input from '@/src/components/dialog/common/Input';

interface ClubDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  infoMessage?: string;
}

const ClubDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  infoMessage,
}: ClubDialogProps) => {
  const mutation = useApiMutation<Club>(url, method, {
    onSuccess: (data) => console.log('successfully:', data),
    onError: (error) => console.error(error),
  });

  if (method === 'DELETE' && infoMessage)
    return <RemoveDialog message={infoMessage} mutation={mutation} />;

  return (
    <CustomDialog
      text={{
        title: dialogTitle,
        btnType: buttonText,
      }}
    >
      <Form initialValue={initialValues} mutation={mutation}>
        {(control, errors) => (
          <>
            <Input.ClubName control={control} errors={errors} />
            <Input.ClubIntro control={control} errors={errors} />
          </>
        )}
      </Form>
    </CustomDialog>
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

const ClubModifyDialog = ({ clubData }: { clubData: Club }) => {
  return (
    <ClubDialog
      url="/club"
      method="PUT"
      initialValues={{
        id: clubData.id,
        name: clubData.name,
        intro: clubData.intro,
      }}
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
      dialogTitle="Club Deletion"
      buttonText="Remove"
      infoMessage={`Removing club -> [id] ${clubData.id}, [name] ${clubData.name}`}
    />
  );
};

ClubDialog.Regist = ClubRegistDialog;
ClubDialog.Modify = ClubModifyDialog;
ClubDialog.Delete = ClubRemoveDialog;

export default ClubDialog;
