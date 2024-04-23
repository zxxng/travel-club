export type RequestData = { [key: string]: string | number };

export interface Membership {
  clubId: number;
  memberEmail: string;
  role: 'Member' | 'President';
  joinDate: string;
}

export interface Club {
  id: number;
  name: string;
  intro: string;
  foundationDay: string;
  membershipList: Membership[];
}
