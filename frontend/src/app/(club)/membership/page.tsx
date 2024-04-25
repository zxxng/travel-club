'use client';

import React from 'react';
import Header from '@/src/components/common/Header';
import SearchBar from '@/src/components/common/SearchBar';
import SectionMenu from '@/src/components/common/SectionMenu';
import MembershipDialog from '@/src/components/dialog/MembershipDialog';
import CalloutUi from '@/src/components/ui/CalloutUi';
import MembershipTable from '@/src/components/table/MembershipTable';
import TargetTable from '@/src/components/table/common/TargetTable';
import { useAtom } from 'jotai';
import { membershipTargetAtom } from '@/src/atom/targetAtom';

const MembershipMenuPage = () => {
  const [target, setTarget] = useAtom(membershipTargetAtom);

  return (
    <>
      {target ? (
        <>
          <Header>
            <Header.Title title="Club Membership Menu" />
            <Header.SubTitle
              subTitle={`[Target Club] ID: ${target.id}, Name: ${target.name}`}
              onClick={() => setTarget(null)}
            />
          </Header>

          <SectionMenu title="Register Membership">
            <MembershipDialog.Regist clubId={target.id} />
          </SectionMenu>
          <SectionMenu title="Find Membership">
            <SearchBar selectList={['Club ID', 'Member Email']} />
            <MembershipTable />
          </SectionMenu>
        </>
      ) : (
        <>
          <Header>
            <Header.Title title="Club Membership Menu" />
          </Header>

          <SectionMenu title="Find Target Club">
            <SearchBar selectList={['ID']} />
            <TargetTable.Club target="membership" />
          </SectionMenu>
          <SectionMenu title="Register Membership">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
          <SectionMenu title="Find Membership">
            <CalloutUi message="You must select a target" />
          </SectionMenu>
        </>
      )}
    </>
  );
};

export default MembershipMenuPage;
