'use client';

import React from 'react';
import useApiMutation from '@/src/hooks/useApiMutation';
import { Posting, type Board, type RequestData } from '@/src/types/apiResponse';
import RemoveDialog from './common/RemoveDialog';
import CustomDialog from '@/src/components/dialog/common/CustomDialog';
import Form from '@/src/components/dialog/common/Form';
import Input from '@/src/components/dialog/common/Input';

interface PostingDialogProps {
  url: string;
  method: 'POST' | 'PUT' | 'DELETE';
  initialValues: RequestData;
  dialogTitle: string;
  buttonText: string;
  infoMessage?: string;
}

const PostingDialog = ({
  url,
  method,
  initialValues,
  dialogTitle,
  buttonText,
  infoMessage,
}: PostingDialogProps) => {
  const mutation = useApiMutation<Board>(url, method, {
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
            <Input.MemberEmail
              control={control}
              errors={errors}
              dataKey="writerEmail"
            />
            <Input.PostingTitle control={control} errors={errors} />
            <Input.PostingContents control={control} errors={errors} />
          </>
        )}
      </Form>
    </CustomDialog>
  );
};

const PostingRegistDialog = ({ boardId }: { boardId: number }) => {
  return (
    <PostingDialog
      url={`/posting/${boardId}`}
      method="POST"
      initialValues={{
        boardId: boardId,
        title: '',
        writerEmail: '',
        contents: '',
      }}
      dialogTitle="Posting Registration"
      buttonText="Register"
    />
  );
};

const PostingModifyDialog = ({ postingData }: { postingData: Posting }) => {
  return (
    <PostingDialog
      url="/posting"
      method="PUT"
      initialValues={{
        postingId: postingData.postingId,
        boardId: postingData.boardId,
        title: postingData.title,
        writerEmail: postingData.writerEmail,
        contents: postingData.contents,
      }}
      dialogTitle="Posting Modification"
      buttonText="Modify"
    />
  );
};

const PostingRemoveDialog = ({ postingData }: { postingData: Posting }) => {
  return (
    <PostingDialog
      url={`/posting/${postingData.postingId}`}
      method="DELETE"
      initialValues={{}}
      dialogTitle="Posting Deletion"
      buttonText="Remove"
      infoMessage={`Removing Posting -> [boardId] ${postingData.boardId}, [postingId] ${postingData.postingId}`}
    />
  );
};

PostingDialog.Regist = PostingRegistDialog;
PostingDialog.Modify = PostingModifyDialog;
PostingDialog.Delete = PostingRemoveDialog;

export default PostingDialog;
