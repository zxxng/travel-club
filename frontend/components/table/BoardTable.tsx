'use client';

import React, { useEffect } from 'react';
import { type Board } from '@/types/apiResponse';
import useApiQuery from '@/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/atom/searchAtom';
import DataTable from './DataTable';

const BoardTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url = `/board${select === 'id' ? `/${keyword}` : `?name=${keyword}`}`;

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
  } = useApiQuery<Board | Board[]>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {boardData && (
        <DataTable title="Board List">
          <DataTable.Header
            headers={[
              'Board ID(Club ID)',
              'Name',
              'Admin Email',
              'Creation date',
              'Management',
            ]}
          />
          {Array.isArray(boardData) ? (
            boardData.map((board) => {
              return (
                <DataTable.BoardRow key={board.clubId} boardData={board} />
              );
            })
          ) : (
            <DataTable.BoardRow boardData={boardData} />
          )}
        </DataTable>
      )}
    </>
  );
};

export default BoardTable;
