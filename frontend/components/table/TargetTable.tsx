import React, { useEffect } from 'react';
import { Board, type Club } from '@/types/apiResponse';
import useApiQuery from '@/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom, useSetAtom } from 'jotai';
import {
  boardTargetAtom,
  keywordAtom,
  membershipTargetAtom,
  postingTargetAtom,
  queryKeyAtom,
  selectAtom,
} from '@/atom/searchAtom';
import DataTable from './DataTable';

const TargetTable = () => {};

const TargetClub = ({ target }: { target: 'membership' | 'board' }) => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const setSelect = useSetAtom(selectAtom);
  const setTarget = useSetAtom(
    target === 'membership' ? membershipTargetAtom : boardTargetAtom,
  );
  const url = `/club/${keyword}`;

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

const TargetBoard = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const setSelect = useSetAtom(selectAtom);
  const setTarget = useSetAtom(postingTargetAtom);
  const url = `/board/${keyword}`;

  useEffect(() => {
    setQueryKey(['get', url]);
  }, [url]);

  useEffect(() => {
    setKeyword('');
    setSelect('ID');
  }, []);

  const {
    data: boardData,
    error,
    isLoading,
  } = useApiQuery<Board>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  const handleSelector = (data: Board) => {
    setTarget(data);
  };

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {boardData && (
        <>
          <DataTable title="">
            <DataTable.Header
              headers={[
                'Board ID(Club ID)',
                'Name',
                'Admin Email',
                'Creation date',
                'Selector',
              ]}
            />
            <DataTable.BoardRow
              boardData={boardData}
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
TargetTable.Board = TargetBoard;

export default TargetTable;
