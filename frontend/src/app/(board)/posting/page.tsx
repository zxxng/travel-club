'use client';

import React from 'react';
import Header from '@/src/components/common/Header';
import SectionMenu from '@/src/components/common/SectionMenu';
import SearchBar from '@/src/components/common/SearchBar';
import { postingTargetAtom } from '@/src/atom/targetAtom';
import { useAtom } from 'jotai';
import TargetTable from '@/src/components/table/common/TargetTable';
import CalloutUi from '@/src/components/ui/CalloutUi';
import PostingDialog from '@/src/components/dialog/PostingDialog';
import PostingTable from '@/src/components/table/PostingTable';

const PostingMenuPage = () => {
  const [target, setTarget] = useAtom(postingTargetAtom);

  return (
    <>
      {target ? (
        <>
          <Header>
            <Header.Title title="Posting Menu" />
            <Header.SubTitle
              subTitle={`[Target Board] ID: ${target.clubId}, Name: ${target.name}`}
              onClick={() => setTarget(null)}
            />
          </Header>

          <SectionMenu title="Register Posting">
            <PostingDialog.Regist boardId={target.clubId} />
          </SectionMenu>
          <SectionMenu title="Find Posting">
            <SearchBar selectList={['ID', 'Board ID']} />
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
