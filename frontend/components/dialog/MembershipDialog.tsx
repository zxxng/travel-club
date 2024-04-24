'use client';

import React from 'react';
import useApiMutation from '@/hooks/useApiMutation';
import { type Membership, type RequestData } from '@/types/apiResponse';
import RemoveDialog from './RemoveDialog';
import DialogUi from '@/components/ui/DialogUi';
import Form from '@/components/dialog/Form';
import Input from '@/components/dialog/Input';
import { Text, Radio } from '@radix-ui/themes';

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
    <DialogUi
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
            <Text className="font-bold" size="2">
              MemberRole
            </Text>
            <Text className="flex gap-1" as="label" size="2">
              <Radio name="role" value="Member" defaultChecked />
              Member
            </Text>
            <Text className="flex gap-1" as="label" size="2">
              <Radio name="role" value="President" />
              President
            </Text>
          </>
        )}
      </Form>
    </DialogUi>
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
        joinDate: '',
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
