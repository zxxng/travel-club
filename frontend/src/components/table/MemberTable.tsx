'use client';

import React, { useEffect } from 'react';
import { type Member } from '@/src/types/apiResponse';
import useApiQuery from '@/src/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/src/atom/searchAtom';
import DataTable from './common/DataTable';

const MemberTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url =
    '/member' + (select === 'Email' ? `/${keyword}` : `?name=${keyword}`);

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
      {memberData && (
        <>
          <DataTable title="Member List">
            <DataTable.Header
              headers={[
                'Member Email(ID)',
                'Name',
                'Nick Name',
                'Phone Number',
                'Birth Day',
                'Management',
              ]}
            />
            {Array.isArray(memberData) ? (
              memberData.map((member) => {
                return (
                  <DataTable.MemberRow key={member.email} memberData={member} />
                );
              })
            ) : (
              <DataTable.MemberRow memberData={memberData} />
            )}
          </DataTable>

          {/* address */}
          {!Array.isArray(memberData) &&
            memberData.addresses &&
            memberData.addresses.length != 0 && (
              <DataTable title="Address List">
                <DataTable.Header
                  headers={[
                    'Zip Code',
                    'Zip Address',
                    'Street Address',
                    'Contry',
                    'Type',
                  ]}
                />
                {memberData.addresses.map((address, idx) => {
                  return (
                    <DataTable.AddressRow
                      key={`${idx}:${address.zipCode}`}
                      addressData={address}
                    />
                  );
                })}
              </DataTable>
            )}

          {/* membership */}
          {!Array.isArray(memberData) &&
            memberData.membershipList &&
            memberData.membershipList.length != 0 && (
              <DataTable title="Membership List">
                <DataTable.Header
                  headers={['Club ID', 'Member Email', 'Role', 'Join Date']}
                />
                {memberData.membershipList.map((membership) => {
                  return (
                    <DataTable.MembershipRow
                      key={`${membership.clubId}:${membership.memberEmail}`}
                      membershipData={membership}
                      option="none"
                    />
                  );
                })}
              </DataTable>
            )}
        </>
      )}
    </>
  );
};

export default MemberTable;
