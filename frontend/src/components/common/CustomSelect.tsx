import React from 'react';
import { Select } from '@radix-ui/themes';
import { useSetAtom } from 'jotai';
import { selectAtom } from '@/src/atom/searchAtom';

interface CustomSelectProps {
  list: string[];
}

const CustomSelect = ({ list }: CustomSelectProps) => {
  const setSelectValue = useSetAtom(selectAtom);

  return (
    <Select.Root
      defaultValue={list[0]}
      onValueChange={(value) => setSelectValue(value)}
    >
      <Select.Trigger color="indigo" variant="soft" />
      <Select.Content color="indigo">
        {list.map((item) => {
          return (
            <Select.Item key={item} value={item}>
              {item}
            </Select.Item>
          );
        })}
      </Select.Content>
    </Select.Root>
  );
};

export default CustomSelect;
