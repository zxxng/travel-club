import React from 'react';
import {
  Controller,
  type Control,
  type FieldErrors,
  type RegisterOptions,
} from 'react-hook-form';
import { Text, TextField } from '@radix-ui/themes';
import { type RequestData } from '@/types/apiResponse';

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
            placeholder={`Enter club ${dataKey}`}
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
}

const NameInput = ({ control, errors }: InputProps) => {
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

const IntroInput = ({ control, errors }: InputProps) => {
  return (
    <Input
      control={control}
      errors={errors}
      dataKey="intro"
      rules={{
        required: 'Please enter club name.',
        minLength: { value: 10, message: 'Intro should be longer than 10' },
      }}
    />
  );
};

Input.Name = NameInput;
Input.Intro = IntroInput;

export default Input;
