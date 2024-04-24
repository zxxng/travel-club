'use client';

import React from 'react';
import Header from '@/components/common/Header';
import SearchBar from '@/components/common/SearchBar';
import SectionMenu from '@/components/common/SectionMenu';
import MemberDialog from '@/components/dialog/MemberDialog';
import MemberTable from '@/components/table/MemberTable';

const MemberMenuPage = () => {
  return (
    <>
      <Header>
        <Header.Title title="Member Menu" />
      </Header>

      <SectionMenu title="Register Member">
        <MemberDialog.Regist />
      </SectionMenu>

      <SectionMenu title="Find Member">
        <SearchBar />
        <MemberTable />
      </SectionMenu>
    </>
  );
};

export default MemberMenuPage;
