import { IApplicationUser, NewApplicationUser } from './application-user.model';

export const sampleWithRequiredData: IApplicationUser = {
  id: 9562,
};

export const sampleWithPartialData: IApplicationUser = {
  id: 14106,
  phoneNumber: 'propre dans gai',
  location: 'sans que',
  notes: 'promettre deçà',
};

export const sampleWithFullData: IApplicationUser = {
  id: 22184,
  phoneNumber: 'main-d’œuvre cadre',
  location: 'concurrence',
  avatar: 'à cause de guide',
  notes: 'pourvu que gigantesque',
};

export const sampleWithNewData: NewApplicationUser = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
