'use client';

import React from 'react';
import Header from '@/components/common/Header';
import SearchBar from '@/components/common/SearchBar';
import SectionMenu from '@/components/common/SectionMenu';
import MembershipDialog from '@/components/dialog/MembershipDialog';
import CalloutUi from '@/components/ui/CalloutUi';
import MembershipTable from '@/components/table/MembershipTable';
import TargetTable from '@/components/table/TargetTable';
import { useAtom } from 'jotai';
import { membershipTargetAtom } from '@/atom/searchAtom';

const MembershipMenuPage = () => {
  const [target, setTarget] = useAtom(membershipTargetAtom);

  return (
    <>
      {target ? (
        <>
          <Header>
            <Header.Title title="Membership Menu" />
            <Header.SubTitle
              subTitle={`Target Club Name: ${target.name}`}
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
            <Header.Title title="Membership Menu" />
          </Header>

          <SectionMenu title="Find Target Club">
            <SearchBar selectList={['ID']} />
            <TargetTable.Club target="membership" />
          </SectionMenu>
          <SectionMenu title="Membership Register">
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
