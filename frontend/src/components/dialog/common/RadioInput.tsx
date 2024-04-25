import React from 'react';
import { Controller, type Control } from 'react-hook-form';
import { Text, Radio } from '@radix-ui/themes';
import { RequestData } from '@/src/types/apiResponse';

interface RadioInputProps {
  control: Control<RequestData>;
}

const RadioInput = ({ control }: RadioInputProps) => {
  return (
    <>
      <Text className="font-bold mb-[-5px]" size="2">
        MemberRole
      </Text>
      <Controller
        name="role"
        control={control}
        rules={{ required: 'Role selection is required' }}
        render={({ field }) => (
          <>
            <label htmlFor="role" className="flex gap-1 items-center text-sm">
              <Radio
                {...field}
                value="Member"
                checked={field.value === 'Member'}
                onChange={field.onChange}
              />
              Member
            </label>
            <label htmlFor="role" className="flex gap-1 items-center text-sm">
              <Radio
                {...field}
                value="President"
                checked={field.value === 'President'}
                onChange={field.onChange}
              />
              President
            </label>
          </>
        )}
      />
    </>
  );
};

export default RadioInput;
