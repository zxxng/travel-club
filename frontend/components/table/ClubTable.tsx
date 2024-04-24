'use client';

import React from 'react';
import { Table } from '@radix-ui/themes';
import { useSearchParams } from 'next/navigation';
import { type Club, type Membership } from '@/types/apiResponse';
import ClubDialog from '../dialog/ClubDialog';
import useApiQuery from '@/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';

const ClubTable = () => {
  const searchParams = useSearchParams();
  const keyword = searchParams.get('keyword');
  const {
    data: clubData,
    error,
    isLoading,
  } = useApiQuery<Club>(`/club?name=${keyword}`, {
    enabled: keyword != null,
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} />;

  return (
    <>
      {clubData ? (
        <>
          <Table.Root className="mb-5">
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
                    <ClubDialog.Modify clubData={clubData} />
                    <ClubDialog.Delete clubData={clubData} />
                  </div>
                </Table.Cell>
              </Table.Row>
            </Table.Body>
          </Table.Root>

          {/* membership */}
          {clubData.membershipList && (
            <Table.Root>
              <Table.Header>
                <Table.Row>
                  <Table.ColumnHeaderCell>Club ID</Table.ColumnHeaderCell>
                  <Table.ColumnHeaderCell>Member Email</Table.ColumnHeaderCell>
                  <Table.ColumnHeaderCell>Role</Table.ColumnHeaderCell>
                  <Table.ColumnHeaderCell>Join Date</Table.ColumnHeaderCell>
                </Table.Row>
              </Table.Header>

              <Table.Body>
                {clubData.membershipList.map(
                  (membership: Membership, index: number) => {
                    return (
                      <Table.Row key={index}>
                        <Table.RowHeaderCell>
                          {membership.clubId}
                        </Table.RowHeaderCell>
                        <Table.Cell>{membership.memberEmail}</Table.Cell>
                        <Table.Cell>{membership.role}</Table.Cell>
                        <Table.Cell>{membership.joinDate}</Table.Cell>
                      </Table.Row>
                    );
                  },
                )}
              </Table.Body>
            </Table.Root>
          )}
        </>
      ) : null}
    </>
  );
};

export default ClubTable;
