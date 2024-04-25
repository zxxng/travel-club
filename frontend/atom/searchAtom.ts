import { Board, Club } from '@/types/apiResponse';
import { atom } from 'jotai';

export const keywordAtom = atom<string>('');

export const selectAtom = atom<string>('id');

export const queryKeyAtom = atom<string[]>([]);

export const membershipTargetAtom = atom<Club | null>(null);

export const boardTargetAtom = atom<Club | null>(null);

export const postingTargetAtom = atom<Board | null>(null);
