import React, { useEffect } from 'react';
import { type Membership } from '@/src/types/apiResponse';
import useApiQuery from '@/src/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/src/atom/searchAtom';
import DataTable from './common/DataTable';

const MembershipTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url =
    '/membership' +
    (select === 'Club ID' ? `?clubId=${keyword}` : `?memberId=${keyword}`);

  useEffect(() => {
    setQueryKey(['get', url]);
  }, [url]);

  useEffect(() => {
    setKeyword('');
    setSelect('Club ID');
  }, []);

  const {
    data: membershipData,
    error,
    isLoading,
  } = useApiQuery<Membership | Membership[]>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {membershipData && (
        <DataTable title="Membership List">
          <DataTable.Header
            headers={[
              'Club ID',
              'Member Email',
              'Role',
              'Join Date',
              'Management',
            ]}
          />

          {Array.isArray(membershipData) ? (
            membershipData.map((membership) => {
              return (
                <DataTable.MembershipRow
                  key={`${membership.clubId}:${membership.memberEmail}`}
                  membershipData={membership}
                />
              );
            })
          ) : (
            <DataTable.MembershipRow membershipData={membershipData} />
          )}
        </DataTable>
      )}
    </>
  );
};

export default MembershipTable;
