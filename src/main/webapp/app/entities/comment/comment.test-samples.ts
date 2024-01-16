import dayjs from 'dayjs/esm';

import { IComment, NewComment } from './comment.model';

export const sampleWithRequiredData: IComment = {
  id: 5500,
  title: 'de par triathlète oups',
  editedBy: 'tellement',
  editedAt: dayjs('2024-01-15'),
};

export const sampleWithPartialData: IComment = {
  id: 17718,
  title: 'hi en bas de',
  visibility: 'vorace',
  editedBy: 'par suite de',
  editedAt: dayjs('2024-01-15'),
  attachments: 'au-delà',
};

export const sampleWithFullData: IComment = {
  id: 28348,
  title: 'même si diablement ronron',
  type: 'bientôt gens',
  visibility: 'plic',
  description: 'que infime rédaction',
  editedBy: 'juriste de sorte que',
  editedAt: dayjs('2024-01-16'),
  attachments: 'cuicui hebdomadaire',
  responseToCommentId: 28984,
};

export const sampleWithNewData: NewComment = {
  title: 'mince si commis de cuisine',
  editedBy: 'en faveur de super',
  editedAt: dayjs('2024-01-16'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
