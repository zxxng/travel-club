'use client';

import React from 'react';
import Header from '@/components/common/Header';
import SectionMenu from '@/components/common/SectionMenu';
import ClubDialog from '@/components/dialog/ClubDialog';
import SearchBar from '@/components/common/SearchBar';
import ClubTable from '@/components/table/ClubTable';

const ClubMenuPage = () => {
  return (
    <div>
      <Header>
        <Header.Title title="Club Menu" />
      </Header>
      <SectionMenu title="Register">
        <ClubDialog.Regist />
      </SectionMenu>
      <SearchBar
        title="Find a Club Name"
        placeholder="Search the Club name..."
      />
      <ClubTable />
    </div>
  );
};

export default ClubMenuPage;
