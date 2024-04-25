'use client';

import React from 'react';
import Header from '@/components/common/Header';
import SectionMenu from '@/components/common/SectionMenu';
import SearchBar from '@/components/common/SearchBar';
import { postingTargetAtom } from '@/atom/searchAtom';
import { useAtom } from 'jotai';
import TargetTable from '@/components/table/TargetTable';
import CalloutUi from '@/components/ui/CalloutUi';
import PostingDialog from '@/components/dialog/PostingDialog';
import PostingTable from '@/components/table/PostingTable';

const PostingMenuPage = () => {
  const [target, setTarget] = useAtom(postingTargetAtom);

  return (
    <>
      {target ? (
        <>
          <Header>
            <Header.Title title="Posting Menu" />
            <Header.SubTitle
              subTitle={`Target Board Name: ${target.name}`}
              onClick={() => setTarget(null)}
            />
          </Header>

          <SectionMenu title="Register Posting">
            <PostingDialog.Regist boardId={target.clubId} />
          </SectionMenu>
          <SectionMenu title="Find Posting">
            <SearchBar selectList={['ID', 'Board Name', 'Club Name']} />
            <PostingTable />
          </SectionMenu>
        </>
      ) : (
        <>
          <Header>
            <Header.Title title="Posting Menu" />
          </Header>

          <SectionMenu title="Find Target Board">
            <SearchBar selectList={['ID']} />
            <TargetTable.Board />
          </SectionMenu>
          <SectionMenu title="Register Posting">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
          <SectionMenu title="Find Posting">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
        </>
      )}
    </>
  );
};

export default PostingMenuPage;
