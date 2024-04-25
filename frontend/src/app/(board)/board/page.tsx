'use client';

import React from 'react';
import Header from '@/src/components/common/Header';
import SectionMenu from '@/src/components/common/SectionMenu';
import SearchBar from '@/src/components/common/SearchBar';
import BoardDialog from '@/src/components/dialog/BoardDialog';
import BoardTable from '@/src/components/table/BoardTable';
import { boardTargetAtom } from '@/src/atom/targetAtom';
import { useAtom } from 'jotai';
import TargetTable from '@/src/components/table/common/TargetTable';
import CalloutUi from '@/src/components/ui/CalloutUi';

const BoardMenuPage = () => {
  const [target, setTarget] = useAtom(boardTargetAtom);

  return (
    <>
      {target ? (
        <>
          <Header>
            <Header.Title title="SocialBoard Menu" />
            <Header.SubTitle
              subTitle={`Target Club Name: ${target.name}`}
              onClick={() => setTarget(null)}
            />
          </Header>

          <SectionMenu title="Register Board">
            <BoardDialog.Regist clubId={target.id} />
          </SectionMenu>
          <SectionMenu title="Find Board">
            <SearchBar selectList={['ID', 'Board Name', 'Club Name']} />
            <BoardTable />
          </SectionMenu>
        </>
      ) : (
        <>
          <Header>
            <Header.Title title="SocialBoard Menu" />
          </Header>

          <SectionMenu title="Find Target Club">
            <SearchBar selectList={['ID']} />
            <TargetTable.Club target="board" />
          </SectionMenu>
          <SectionMenu title="Register Board">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
          <SectionMenu title="Find Board">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
        </>
      )}
    </>
  );
};

export default BoardMenuPage;
