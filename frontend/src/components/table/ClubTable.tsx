'use client';

import React, { useEffect } from 'react';
import { type Club } from '@/src/types/apiResponse';
import useApiQuery from '@/src/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/src/atom/searchAtom';
import DataTable from './common/DataTable';

const ClubTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url = `/club${select === 'ID' ? `/${keyword}` : `?name=${keyword}`}`;

  useEffect(() => {
    setQueryKey(['get', url]);
  }, [url]);

  useEffect(() => {
    setKeyword('');
    setSelect('ID');
  }, []);

  const {
    data: clubData,
    error,
    isLoading,
  } = useApiQuery<Club>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {clubData && (
        <>
          <DataTable title="Club List">
            <DataTable.Header
              headers={[
                'Club ID',
                'Name',
                'Intro',
                'Foundation Day',
                'Management',
              ]}
            />
            <DataTable.ClubRow clubData={clubData} />
          </DataTable>

          {/* membership */}
          {clubData.membershipList && clubData.membershipList.length != 0 && (
            <DataTable title="Membership List">
              <DataTable.Header
                headers={['Club ID', 'Member Email', 'Role', 'Join Date']}
              />
              {clubData.membershipList.map((membership) => {
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

export default ClubTable;
