'use client';

import React from 'react';
import useApiMutation from '@/hooks/useApiMutation';
import { type Board, type RequestData } from '@/types/apiResponse';
import RemoveDialog from './RemoveDialog';
import DialogUi from '@/components/ui/DialogUi';
import Form from '@/components/dialog/Form';
import Input from '@/components/dialog/Input';

interface BoardDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  infoMessage?: string;
}

const BoardDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  infoMessage,
}: BoardDialogProps) => {
  const mutation = useApiMutation<Board>(url, method, {
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
            <Input.BoardName control={control} errors={errors} />
            <Input.MemberEmail
              control={control}
              errors={errors}
              dataKey="adminEmail"
            />
          </>
        )}
      </Form>
    </DialogUi>
  );
};

const BoardRegistDialog = ({ clubId }: { clubId: number }) => {
  return (
    <BoardDialog
      url="/board"
      method="POST"
      initialValues={{
        clubId: clubId,
        name: '',
        adminEmail: '',
      }}
      dialogTitle="Board Registration"
      buttonText="Register"
    />
  );
};

const BoardModifyDialog = ({ boardData }: { boardData: Board }) => {
  return (
    <BoardDialog
      url="/board"
      method="PUT"
      initialValues={{
        clubId: boardData.clubId,
        name: boardData.name,
        adminEmail: boardData.adminEmail,
      }}
      dialogTitle="Board Modification"
      buttonText="Modify"
    />
  );
};

const BoardRemoveDialog = ({ boardData }: { boardData: Board }) => {
  return (
    <BoardDialog
      url={`/board/${boardData.clubId}`}
      method="DELETE"
      initialValues={{}}
      dialogTitle="Board Deletion"
      buttonText="Remove"
      infoMessage={`Removing Board -> [clubId] ${boardData.clubId}, [name] ${boardData.name}`}
    />
  );
};

BoardDialog.Regist = BoardRegistDialog;
BoardDialog.Modify = BoardModifyDialog;
BoardDialog.Delete = BoardRemoveDialog;

export default BoardDialog;
