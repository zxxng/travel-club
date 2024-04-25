import { atom } from 'jotai';
import { Board, Club } from '@/src/types/apiResponse';

export const membershipTargetAtom = atom<Club | null>(null);

export const boardTargetAtom = atom<Club | null>(null);

export const postingTargetAtom = atom<Board | null>(null);
