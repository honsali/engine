package dev.cruding.engine.printer.impl.commun;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeStorePrinter extends Printer {

    public void print() {
        Flow f = new Flow();
        f.__("import { AnyAction, combineReducers, configureStore, ThunkAction } from '@reduxjs/toolkit';");
        f.L("import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';");
        f.L("");
        f.L("import notificationMiddleWare from 'core/util/notificationMiddleWare';");
        f.L("import StoreAuth from 'modele/auth/auth.store';");
        f.L("import StoreSession from 'modele/session/session.store';");
        for (Page p : pageList()) {
            f.L("import Mdl" + p.uc + " from 'modules/" + p.path + "/Mdl" + p.uc + "';");
        }
        f.L("");
        f.L("const rootReducer = combineReducers({");
        f.L____("storeAuth: StoreAuth,");
        f.L____("storeSession: StoreSession,");
        for (Page p : pageList()) {
            f.L____("mdl" + p.uc + ": Mdl" + p.uc + ",");
        }
        f.L("});");
        f.L("");
        f.L("const store = configureStore({");
        f.L____("reducer: rootReducer,");
        f.L____("middleware: (getDefaultMiddleware) => getDefaultMiddleware().prepend(notificationMiddleWare),");
        f.L("});");
        f.L("");
        f.L("const getStore = () => {");
        f.L____("return store;");
        f.L("};");
        f.L("");
        f.L("export type IRootState = ReturnType<typeof rootReducer>;");
        f.L("export type AppDispatch = typeof store.dispatch;");
        f.L("");
        f.L("export const useAppSelector: TypedUseSelectorHook<IRootState> = useSelector;");
        f.L("export const useAppDispatch = () => useDispatch<AppDispatch>();");
        f.L("export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, IRootState, unknown, AnyAction>;");
        f.L("");
        f.L("export default getStore;");

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/core/config/store.config.ts");
    }
}