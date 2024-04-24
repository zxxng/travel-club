import React, { useEffect } from 'react';
import { type Club } from '@/types/apiResponse';
import useApiQuery from '@/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom, useSetAtom } from 'jotai';
import {
  boardTargetAtom,
  keywordAtom,
  membershipTargetAtom,
  queryKeyAtom,
  selectAtom,
} from '@/atom/searchAtom';
import DataTable from './DataTable';

const TargetTable = () => {};

interface TargetClubProps {
  target: 'membership' | 'board';
}

const TargetClub = ({ target }: TargetClubProps) => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const setTarget = useSetAtom(
    target === 'membership' ? membershipTargetAtom : boardTargetAtom,
  );
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

  const handleSelector = (data: Club) => {
    setTarget(data);
  };

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {clubData && (
        <>
          <DataTable title="">
            <DataTable.Header
              headers={[
                'Club ID',
                'Name',
                'Intro',
                'Foundation Day',
                'Selector',
              ]}
            />
            <DataTable.ClubRow
              clubData={clubData}
              option="selector"
              onClick={handleSelector}
            />
          </DataTable>
        </>
      )}
    </>
  );
};

TargetTable.Club = TargetClub;

export default TargetTable;
