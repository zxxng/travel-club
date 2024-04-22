import React, { useRef } from 'react';
import DialogUi from '../ui/DialogUi';
import { Dialog, Flex, Text, TextField } from '@radix-ui/themes';

interface ClubDialog {
  btnType?: 'Register' | 'Modify';
  name?: string;
  intro?: string;
}

const ClubDialog = ({ btnType = 'Register', name, intro }: ClubDialog) => {
  const clubNameRef = useRef<HTMLInputElement>(null);
  const clubIntroRef = useRef<HTMLInputElement>(null);

  const handleSave = () => {
    const clubData = {
      name: clubNameRef.current?.value,
      intro: clubIntroRef.current?.value,
    };

    fetch('http://localhost:8080/club', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(clubData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Club data sent successfully:', data);
      })
      .catch((error) => {
        console.error('Error sending club data:', error);
      });
  };

  return (
    <>
      <DialogUi btnText={btnType} onClick={handleSave}>
        <Dialog.Title>{`Club ${btnType}`}</Dialog.Title>

        <Flex direction="column" gap="3" className="mb-3">
          <label>
            <Text as="div" size="2" mb="1" weight="bold">
              Name
            </Text>
            <TextField.Root
              defaultValue={name}
              placeholder="Enter club name"
              minLength={3}
              ref={clubNameRef}
            />
          </label>
        </Flex>

        <Flex direction="column" gap="3" className="mb-3">
          <label>
            <Text as="div" size="2" mb="1" weight="bold">
              Intro
            </Text>
            <TextField.Root
              defaultValue={intro}
              placeholder="Enter Intro name"
              minLength={10}
              ref={clubIntroRef}
            />
          </label>
        </Flex>
      </DialogUi>
    </>
  );
};

export default ClubDialog;
