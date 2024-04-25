'use client';

import React from 'react';
import useApiMutation from '@/src/hooks/useApiMutation';
import { type Membership, type RequestData } from '@/src/types/apiResponse';
import RemoveDialog from './common/RemoveDialog';
import CustomDialog from '@/src/components/dialog/common/CustomDialog';
import Form from '@/src/components/dialog/common/Form';
import Input from '@/src/components/dialog/common/Input';
import RadioInput from '@/src/components/dialog/common/RadioInput';

interface MembershipDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  infoMessage?: string;
}

const MembershipDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  infoMessage,
}: MembershipDialogProps) => {
  const mutation = useApiMutation<Membership>(url, method, {
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
            {method === 'POST' && (
              <Input.MemberEmail
                control={control}
                errors={errors}
                dataKey="memberEmail"
              />
            )}
            <RadioInput control={control} errors={errors} />
          </>
        )}
      </Form>
    </CustomDialog>
  );
};

const MembershipRegistDialog = ({ clubId }: { clubId: number }) => {
  return (
    <MembershipDialog
      url="/membership"
      method="POST"
      initialValues={{
        clubId: clubId,
        memberEmail: '',
        role: 'Member',
      }}
      dialogTitle="Membership Registration"
      buttonText="Register"
    />
  );
};

const MembershipModifyDialog = ({
  membershipData,
}: {
  membershipData: Membership;
}) => {
  return (
    <MembershipDialog
      url="/membership"
      method="PUT"
      initialValues={{
        clubId: membershipData.clubId,
        memberEmail: membershipData.memberEmail,
        role: membershipData.role,
      }}
      dialogTitle="Membership Modification"
      buttonText="Modify"
    />
  );
};

const MembershipRemoveDialog = ({
  membershipData,
}: {
  membershipData: Membership;
}) => {
  return (
    <MembershipDialog
      url={`/membership/${membershipData.clubId}/${membershipData.memberEmail}`}
      method="DELETE"
      initialValues={{}}
      dialogTitle="Membership Deletion"
      buttonText="Remove"
      infoMessage={`Removing Membership -> [club id] ${membershipData.clubId}, [member email] ${membershipData.memberEmail}`}
    />
  );
};

MembershipDialog.Regist = MembershipRegistDialog;
MembershipDialog.Modify = MembershipModifyDialog;
MembershipDialog.Delete = MembershipRemoveDialog;

export default MembershipDialog;
