import React from 'react';
import { Table, Button } from '@radix-ui/themes';
import { Club, Member, Membership, Board, Posting } from '@/types/apiResponse';
import ClubDialog from '../dialog/ClubDialog';
import MemberDialog from '../dialog/MemberDialog';
import BoardDialog from '../dialog/BoardDialog';
import MembershipDialog from '../dialog/MembershipDialog';
import PostingDialog from '../dialog/PostingDialog';

type Option = 'management' | 'selector' | 'none';
interface DataTableProps {
  title: string;
  children: React.ReactNode;
}

const DataTable = ({ title, children }: DataTableProps) => {
  return (
    <>
      <h3 className="text-medium-gray text-base font-semibold mt-5 px-2">
        {title}
      </h3>
      <Table.Root>{children}</Table.Root>
    </>
  );
};

const Header = ({ headers }: { headers: string[] }) => {
  return (
    <Table.Header>
      <Table.Row>
        {headers.map((header, idx) => {
          return (
            <Table.ColumnHeaderCell key={`${idx}:${header}`}>
              {header}
            </Table.ColumnHeaderCell>
          );
        })}
      </Table.Row>
    </Table.Header>
  );
};

const ClubRow = ({
  clubData,
  option = 'management',
  onClick,
}: {
  clubData: Club;
  option?: Option;
  onClick?: (data: Club) => void;
}) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{clubData.id}</Table.RowHeaderCell>
        <Table.Cell>{clubData.name}</Table.Cell>
        <Table.Cell>{clubData.intro}</Table.Cell>
        <Table.Cell>{clubData.foundationDay}</Table.Cell>
        <Table.Cell>
          {option === 'management' ? (
            <div className="flex gap-1">
              <ClubDialog.Modify clubData={clubData} />
              <ClubDialog.Delete clubData={clubData} />
            </div>
          ) : (
            <Button onClick={() => onClick?.(clubData)}>Selector</Button>
          )}
        </Table.Cell>
      </Table.Row>
    </Table.Body>
  );
};

const MemberRow = ({ memberData }: { memberData: Member }) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{memberData.email}</Table.RowHeaderCell>
        <Table.Cell>{memberData.name}</Table.Cell>
        <Table.Cell>{memberData.nickName}</Table.Cell>
        <Table.Cell>{memberData.phoneNumber}</Table.Cell>
        <Table.Cell>{memberData.birthDay}</Table.Cell>
        <Table.Cell>
          <div className="flex gap-1">
            <MemberDialog.Modify memberData={memberData} />
            <MemberDialog.Delete memberData={memberData} />
          </div>
        </Table.Cell>
      </Table.Row>
    </Table.Body>
  );
};

const MembershipRow = ({
  membershipData,
  option = 'management',
}: {
  membershipData: Membership;
  option?: Option;
}) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{membershipData.clubId}</Table.RowHeaderCell>
        <Table.Cell>{membershipData.memberEmail}</Table.Cell>
        <Table.Cell>{membershipData.role}</Table.Cell>
        <Table.Cell>{membershipData.joinDate}</Table.Cell>
        {option === 'management' ? (
          <Table.Cell>
            <div className="flex gap-1">
              <MembershipDialog.Modify membershipData={membershipData} />
              <MembershipDialog.Delete membershipData={membershipData} />
            </div>
          </Table.Cell>
        ) : null}
      </Table.Row>
    </Table.Body>
  );
};

const BoardRow = ({
  boardData,
  option = 'management',
  onClick,
}: {
  boardData: Board;
  option?: Option;
  onClick?: (data: Board) => void;
}) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{boardData.clubId}</Table.RowHeaderCell>
        <Table.Cell>{boardData.name}</Table.Cell>
        <Table.Cell>{boardData.adminEmail}</Table.Cell>
        <Table.Cell>{boardData.createDate}</Table.Cell>
        <Table.Cell>
          {option === 'management' ? (
            <div className="flex gap-1">
              <BoardDialog.Modify boardData={boardData} />
              <BoardDialog.Delete boardData={boardData} />
            </div>
          ) : (
            <Button onClick={() => onClick?.(boardData)}>Selector</Button>
          )}
        </Table.Cell>
      </Table.Row>
    </Table.Body>
  );
};

const PostingRow = ({ postingData }: { postingData: Posting }) => {
  return (
    <Table.Body>
      <Table.Row>
        <Table.RowHeaderCell>{postingData.postingId}</Table.RowHeaderCell>
        <Table.Cell>{postingData.boardId}</Table.Cell>
        <Table.Cell>{postingData.writerEmail}</Table.Cell>
        <Table.Cell>{postingData.title}</Table.Cell>
        <Table.Cell>{postingData.contents}</Table.Cell>
        <Table.Cell>{postingData.writtenDate}</Table.Cell>
        <Table.Cell>{postingData.readCount}</Table.Cell>
        <Table.Cell>
          <div className="flex gap-1">
            <PostingDialog.Modify postingData={postingData} />
            <PostingDialog.Delete postingData={postingData} />
          </div>
        </Table.Cell>
      </Table.Row>
    </Table.Body>
  );
};

DataTable.Header = Header;
DataTable.ClubRow = ClubRow;
DataTable.MemberRow = MemberRow;
DataTable.MembershipRow = MembershipRow;
DataTable.BoardRow = BoardRow;
DataTable.PostingRow = PostingRow;

export default DataTable;
