package org.apache.maven.scm.provider.clearcase.util;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.scm.providers.clearcase.settings.Settings;
import org.apache.maven.scm.providers.clearcase.settings.io.xpp3.ClearcaseXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 */
public class ClearCaseUtil
{
    private ClearCaseUtil()
    {
    }

    public static Settings getSettings()
    {
        File scmUserDir = new File( System.getProperty( "user.dir" ), ".scm" );
        File settingsFile = new File( scmUserDir, "clearcase-settings.xml" );

        if ( settingsFile.exists() )
        {
            ClearcaseXpp3Reader reader = new ClearcaseXpp3Reader();
            try
            {
                return reader.read( new FileReader( settingsFile ) );
            }
            catch ( FileNotFoundException e )
            {
            }
            catch ( IOException e )
            {
            }
            catch ( XmlPullParserException e )
            {
                String message = settingsFile.getAbsolutePath() + " isn't well formed. SKIPPED." + e.getMessage();

                System.out.println( message );
            }
        }

        return new Settings();
    }
}