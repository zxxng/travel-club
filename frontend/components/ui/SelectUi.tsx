import React from 'react';
import { Select } from '@radix-ui/themes';
import { useAtom } from 'jotai';
import { selectAtom } from '@/atom/searchAtom';

interface SelectUiProps {
  list: string[];
}

const SelectUi = ({ list }: SelectUiProps) => {
  const [selectedValue, setSelectValue] = useAtom(selectAtom);

  return (
    <Select.Root
      defaultValue={selectedValue}
      onValueChange={(value) => setSelectValue(value)}
    >
      <Select.Trigger color="indigo" variant="soft" />
      <Select.Content color="indigo">
        {list.map((item) => {
          return (
            <Select.Item key={item} value={item.toLocaleLowerCase()}>
              {item}
            </Select.Item>
          );
        })}
      </Select.Content>
    </Select.Root>
  );
};

export default SelectUi;
