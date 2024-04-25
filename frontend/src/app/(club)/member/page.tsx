'use client';

import React from 'react';
import Header from '@/src/components/common/Header';
import SearchBar from '@/src/components/common/SearchBar';
import SectionMenu from '@/src/components/common/SectionMenu';
import MemberDialog from '@/src/components/dialog/MemberDialog';
import MemberTable from '@/src/components/table/MemberTable';

const MemberMenuPage = () => {
  return (
    <>
      <Header>
        <Header.Title title="Community Member Menu" />
      </Header>

      <SectionMenu title="Register Member">
        <MemberDialog.Regist />
      </SectionMenu>

      <SectionMenu title="Find Member">
        <SearchBar selectList={['Email', 'Name']} />
        <MemberTable />
      </SectionMenu>
    </>
  );
};

export default MemberMenuPage;
