'use client';

import React from 'react';
import useApiMutation from '@/hooks/useApiMutation';
import { type Member, type RequestData } from '@/types/apiResponse';
import RemoveDialog from './RemoveDialog';
import DialogUi from '@/components/ui/DialogUi';
import Form from '@/components/common/Form';
import Input from '@/components/common/Input';

interface MemberDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  infoMessage?: string;
}

const MemberDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  infoMessage,
}: MemberDialogProps) => {
  const mutation = useApiMutation<Member>(url, method, {
    onSuccess: (data) => console.log('successfully:', data),
    onError: (error) => console.error(error),
  });

  if (method === 'DELETE' && infoMessage)
    return <RemoveDialog message={infoMessage} mutation={mutation} />;

  return (
    <DialogUi
      text={{
        title: dialogTitle,
        btnType: buttonText,
      }}
    >
      <Form initialValue={initialValues} mutation={mutation}>
        {(control, errors) => (
          <>
            {method === 'POST' ? (
              <Input.MemberEmail control={control} errors={errors} />
            ) : null}
            <Input.MemberName control={control} errors={errors} />
            <Input.MemberNickName control={control} errors={errors} />
            <Input.MemberPhoneNumber control={control} errors={errors} />
            <Input.MemberBirthDay control={control} errors={errors} />
          </>
        )}
      </Form>
    </DialogUi>
  );
};

const MemberRegistDialog = () => {
  return (
    <MemberDialog
      url="/member"
      method="POST"
      initialValues={{
        email: '',
        name: '',
        nickName: '',
        phoneNumber: '',
        birthDay: '',
      }}
      dialogTitle="Member Registration"
      buttonText="Register"
    />
  );
};

const MemberModifyDialog = ({ memberData }: { memberData: Member }) => {
  return (
    <MemberDialog
      url="/member"
      method="PUT"
      initialValues={{
        email: memberData.email,
        name: memberData.name,
        nickName: memberData.nickName,
        phoneNumber: memberData.phoneNumber,
        birthDay: memberData.birthDay,
      }}
      dialogTitle="Member Modification"
      buttonText="Modify"
    />
  );
};

const MemberRemoveDialog = ({ memberData }: { memberData: Member }) => {
  return (
    <MemberDialog
      url={`/member/${memberData.email}`}
      method="DELETE"
      initialValues={{}}
      dialogTitle="Member Deletion"
      buttonText="Remove"
      infoMessage={`Removing member -> [email] ${memberData.email}`}
    />
  );
};

MemberDialog.Regist = MemberRegistDialog;
MemberDialog.Modify = MemberModifyDialog;
MemberDialog.Delete = MemberRemoveDialog;

export default MemberDialog;
