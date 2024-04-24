'use client';

import React, { useEffect } from 'react';
import { Table } from '@radix-ui/themes';
import { type Member } from '@/types/apiResponse';
import useApiQuery from '@/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/atom/searchAtom';
import MembershipTable from './MembershipTable';
import MemberDialog from '../dialog/MemberDialog';

const MemberInfo = ({ memberData }: { memberData: Member }) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{memberData.email}</Table.RowHeaderCell>
        <Table.Cell>{memberData.name}</Table.Cell>
        <Table.Cell>{memberData.nickName}</Table.Cell>
        <Table.Cell>{memberData.phoneNumber}</Table.Cell>
        <Table.Cell>{memberData.birthDay}</Table.Cell>
        <Table.Cell>
          <div className="flex gap-1">
            <MemberDialog.Modify memberData={memberData} />
            <MemberDialog.Delete memberData={memberData} />
          </div>
        </Table.Cell>
      </Table.Row>
    </Table.Body>
  );
};

const MemberTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url = `/member${select === 'Email' ? `/${keyword}` : `?name=${keyword}`}`;

  useEffect(() => {
    setQueryKey(['get', url]);
  }, [url]);

  useEffect(() => {
    setKeyword('');
    setSelect('Email');
  }, []);

  const {
    data: memberData,
    error,
    isLoading,
  } = useApiQuery<Member | Member[]>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {memberData ? (
        <>
          <h3 className="text-medium-gray text-lg font-semibold mt-5 px-2">
            Member List
          </h3>
          <Table.Root>
            <Table.Header>
              <Table.Row>
                <Table.ColumnHeaderCell>
                  Member Email(ID)
                </Table.ColumnHeaderCell>
                <Table.ColumnHeaderCell>Name</Table.ColumnHeaderCell>
                <Table.ColumnHeaderCell>Nick Name</Table.ColumnHeaderCell>
                <Table.ColumnHeaderCell>Phone Number</Table.ColumnHeaderCell>
                <Table.ColumnHeaderCell>Birth Day</Table.ColumnHeaderCell>
                <Table.ColumnHeaderCell>Management</Table.ColumnHeaderCell>
              </Table.Row>
            </Table.Header>

            {Array.isArray(memberData) ? (
              memberData.map((member) => {
                return <MemberInfo key={member.email} memberData={member} />;
              })
            ) : (
              <MemberInfo memberData={memberData} />
            )}
          </Table.Root>

          {!Array.isArray(memberData) &&
            memberData.membershipDtoList &&
            memberData.membershipDtoList.length != 0 && (
              <MembershipTable membershipList={memberData.membershipDtoList} />
            )}
        </>
      ) : null}
    </>
  );
};

export default MemberTable;
