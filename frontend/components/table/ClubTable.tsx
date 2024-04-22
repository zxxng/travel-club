'use client';

import React, { useEffect, useState } from 'react';
import { Table, Callout } from '@radix-ui/themes';
import { useSearchParams } from 'next/navigation';
import { type Club } from '@/types/apiResponse';
import { Info } from 'lucide-react';
import ClubDialog from '../dialog/ClubDialog';
import AlertDialogUi from '../ui/AlertDialogUi';

const ClubTable = () => {
  const searchParams = useSearchParams();
  const keyword = searchParams.get('keyword');
  const [clubData, setClubData] = useState<Club | null>(null);

  useEffect(() => {
    if (keyword) {
      fetch(`http://localhost:8080/club?name=${keyword}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then((data) => {
          console.log('Club data sent successfully:', data);
          setClubData(data);
        })
        .catch((error) => {
          console.error('Error sending club data:', error);
          setClubData(null);
        });
    } else {
      setClubData(null);
    }
  }, [keyword]);

  return (
    <>
      {clubData ? (
        <Table.Root>
          <Table.Header>
            <Table.Row>
              <Table.ColumnHeaderCell>Club ID</Table.ColumnHeaderCell>
              <Table.ColumnHeaderCell>Name</Table.ColumnHeaderCell>
              <Table.ColumnHeaderCell>Intro</Table.ColumnHeaderCell>
              <Table.ColumnHeaderCell>Foundation Day</Table.ColumnHeaderCell>
              <Table.ColumnHeaderCell>Management</Table.ColumnHeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            <Table.Row>
              <Table.RowHeaderCell>{clubData.id}</Table.RowHeaderCell>
              <Table.Cell>{clubData.name}</Table.Cell>
              <Table.Cell>{clubData.intro}</Table.Cell>
              <Table.Cell>{clubData.foundationDay}</Table.Cell>
              <Table.Cell>
                <div className="flex gap-1">
                  <ClubDialog
                    btnType="Modify"
                    name={clubData.name}
                    intro={clubData.intro}
                  />
                  <AlertDialogUi
                    message={`Removing a club --> ${clubData.name}`}
                    onClick={() => {
                      console.log('delete!');
                    }}
                  />
                </div>
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table.Root>
      ) : (
        <Callout.Root>
          <Callout.Icon>
            <Info />
          </Callout.Icon>
          <Callout.Text>{`No such club with name -> ${keyword}`}</Callout.Text>
        </Callout.Root>
      )}
    </>
  );
};

export default ClubTable;
