import { useContext } from 'react';
import { AccountContext } from '../contexts/AccountContext';

export const useAccount = () => {
    const context = useContext(AccountContext);
    if (!context) {
      throw new Error('useAccount must be used within an AccountContextProvider');
    }
    return context;
};