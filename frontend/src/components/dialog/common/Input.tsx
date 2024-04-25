import React from 'react';
import {
  Controller,
  type Control,
  type FieldErrors,
  type RegisterOptions,
} from 'react-hook-form';
import { Text, TextField } from '@radix-ui/themes';
import { type RequestData } from '@/src/types/apiResponse';

interface InputUiProps {
  control: Control<RequestData>;
  errors: FieldErrors<RequestData>;
  dataKey: string;
  rules: RegisterOptions;
}

const Input = ({ control, dataKey, rules, errors }: InputUiProps) => {
  return (
    <label htmlFor={dataKey}>
      <Text as="div" size="2" mb="1" weight="bold">
        {dataKey.charAt(0).toUpperCase() + dataKey.slice(1)}
      </Text>
      <Controller
        name={dataKey}
        control={control}
        rules={rules}
        render={({ field }) => (
          <TextField.Root
            id={dataKey}
            placeholder={`Enter ${dataKey}`}
            {...field}
          />
        )}
      />
      {errors[dataKey] && (
        <Text className="text-accent-red m-1" as="div" size="1">
          {errors[dataKey]?.message}
        </Text>
      )}
    </label>
  );
};

interface InputProps {
  control: Control<RequestData>;
  errors: FieldErrors<RequestData>;
  dataKey?: string;
}

const ClubNameInput = ({ control, errors }: InputProps) => {
  return (
    <Input
      control={control}
      errors={errors}
      dataKey="name"
      rules={{
        required: 'Please enter club name.',
        minLength: { value: 5, message: 'Name should be longer than 5' },
      }}
    />
  );
};

const ClubIntroInput = ({ control, errors }: InputProps) => {
  return (
    <Input
      control={control}
      errors={errors}
      dataKey="intro"
      rules={{
        required: 'Please enter club intro.',
        minLength: { value: 10, message: 'Intro should be longer than 10' },
      }}
    />
  );
};

const MemberEmailInput = ({
  control,
  errors,
  dataKey = 'email',
}: InputProps) => {
  return (
    <Input
      control={control}
      errors={errors}
      dataKey={dataKey}
      rules={{
        required: `Please enter member email.`,
      }}
    />
  );
};

const MemberNameInput = ({ control, errors }: InputProps) => {
  return <Input control={control} errors={errors} dataKey="name" rules={{}} />;
};

const MemberNickNameInput = ({ control, errors }: InputProps) => {
  return (
    <Input control={control} errors={errors} dataKey="nickName" rules={{}} />
  );
};

const MemberPhoneNumberInput = ({ control, errors }: InputProps) => {
  return (
    <Input control={control} errors={errors} dataKey="phoneNumber" rules={{}} />
  );
};

const MemberBirthdayInput = ({ control, errors }: InputProps) => {
  return (
    <Input control={control} errors={errors} dataKey="birthDay" rules={{}} />
  );
};

const BoardNameInput = ({ control, errors }: InputProps) => {
  return <Input control={control} errors={errors} dataKey="name" rules={{}} />;
};

const PostingTitleInput = ({ control, errors }: InputProps) => {
  return <Input control={control} errors={errors} dataKey="title" rules={{}} />;
};

const PostingContentsInput = ({ control, errors }: InputProps) => {
  return (
    <Input control={control} errors={errors} dataKey="contents" rules={{}} />
  );
};

Input.ClubName = ClubNameInput;
Input.ClubIntro = ClubIntroInput;
Input.MemberEmail = MemberEmailInput;
Input.MemberName = MemberNameInput;
Input.MemberNickName = MemberNickNameInput;
Input.MemberPhoneNumber = MemberPhoneNumberInput;
Input.MemberBirthDay = MemberBirthdayInput;
Input.BoardName = BoardNameInput;
Input.PostingTitle = PostingTitleInput;
Input.PostingContents = PostingContentsInput;

export default Input;
