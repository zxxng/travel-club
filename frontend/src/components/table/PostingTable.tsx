'use client';

import React, { useEffect } from 'react';
import { type Posting } from '@/src/types/apiResponse';
import useApiQuery from '@/src/hooks/useApiQuery';
import CalloutUi from '../ui/CalloutUi';
import SkeletonUi from '../ui/SkeletonUi';
import { useAtom } from 'jotai';
import { keywordAtom, queryKeyAtom, selectAtom } from '@/src/atom/searchAtom';
import DataTable from './common/DataTable';

const PostingTable = () => {
  const [keyword, setKeyword] = useAtom(keywordAtom);
  const [select, setSelect] = useAtom(selectAtom);
  const [queryKey, setQueryKey] = useAtom(queryKeyAtom);
  const url = `/posting${select === 'ID' ? `/${keyword}` : `?name=${keyword}`}`;

  useEffect(() => {
    setQueryKey(['get', url]);
  }, [url]);

  useEffect(() => {
    setKeyword('');
    setSelect('ID');
  }, []);

  const {
    data: postingData,
    error,
    isLoading,
  } = useApiQuery<Posting | Posting[]>(url, {
    queryKey: queryKey,
    enabled: keyword != '',
    retry: 1,
  });

  if (isLoading) return <SkeletonUi />;
  if (error) return <CalloutUi message={`${error.message}`} className="mt-5" />;

  return (
    <>
      {postingData && (
        <DataTable title="Posting List">
          <DataTable.Header
            headers={[
              'Posting ID',
              'Board ID(Club ID)',
              'Writer Email',
              'Title',
              'Contents',
              'Written Date',
              'Read Count',
            ]}
          />
          {Array.isArray(postingData) ? (
            postingData.map((posting) => {
              return (
                <DataTable.PostingRow
                  key={posting.postingId}
                  postingData={posting}
                />
              );
            })
          ) : (
            <DataTable.PostingRow postingData={postingData} />
          )}
        </DataTable>
      )}
    </>
  );
};

export default PostingTable;
