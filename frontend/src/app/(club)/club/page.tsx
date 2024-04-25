'use client';

import React from 'react';
import Header from '@/src/components/common/Header';
import SectionMenu from '@/src/components/common/SectionMenu';
import ClubDialog from '@/src/components/dialog/ClubDialog';
import SearchBar from '@/src/components/common/SearchBar';
import ClubTable from '@/src/components/table/ClubTable';

const ClubMenuPage = () => {
  return (
    <>
      <Header>
        <Header.Title title="Club Menu" />
      </Header>

      <SectionMenu title="Register Club">
        <ClubDialog.Regist />
      </SectionMenu>

      <SectionMenu title="Find Club">
        <SearchBar selectList={['ID', 'Name']} />
        <ClubTable />
      </SectionMenu>
    </>
  );
};

export default ClubMenuPage;
