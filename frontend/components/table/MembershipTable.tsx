import React from 'react';
import { Table } from '@radix-ui/themes';
import { Membership } from '@/types/apiResponse';

interface MembershipTableProps {
  membershipList: Membership[];
}

const MembershipTable = ({ membershipList }: MembershipTableProps) => {
  return (
    <>
      <h3 className="text-medium-gray text-lg font-semibold mt-5 px-2">
        Membership List
      </h3>
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
          {membershipList.map((membership: Membership, index: number) => {
            return (
              <Table.Row key={index}>
                <Table.RowHeaderCell>{membership.clubId}</Table.RowHeaderCell>
                <Table.Cell>{membership.memberEmail}</Table.Cell>
                <Table.Cell>{membership.role}</Table.Cell>
                <Table.Cell>{membership.joinDate}</Table.Cell>
              </Table.Row>
            );
          })}
        </Table.Body>
      </Table.Root>
    </>
  );
};

export default MembershipTable;
