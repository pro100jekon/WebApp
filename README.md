# channel-simplification-stub

**How to launch stub locally using Intellij IDEA**
================================================
1. Open ``Application.java`` class. Open this window by right mouse button and select ``Create 'Application'``.
    
    ![Image1](/images/1.jpg)
1. Enter this command into ``VM options`` input field: ``-Dspring.profiles.active=local``.
1. Copy this content
    ````
    PUBLIC_KEY_PKCS8=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4a4swRq1J67HAii+wgRbSE6QKoyS30YWWQs4soKmqvD9dpq9PrBQs1aeBDIZgrdhXta3o85jDwDRs8IIOitaEti+7kB0bR7dkznEXGe5BKDeDiS305AtKvf7tclHTtN4byr0PaIvC5IA26Mx9BQEd/wfLpT38gHwuH24stwaCwwIDAQAB;PRIVATE_KEY_PKCS8=MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhrizBGrUnrscCKL7CBFtITpAqjJLfRhZZCziygqaq8P12mr0+sFCzVp4EMhmCt2Fe1rejzmMPANGzwgg6K1oS2L7uQHRtHt2TOcRcZ7kEoN4OJLfTkC0q9/u1yUdO03hvKvQ9oi8LkgDbozH0FAR3/B8ulPfyAfC4fbiy3BoLDAgMBAAECgYBb6+7Sv0e8oqhUygv/NDjFuVh4nNy+asblIKNXhzVKjs4exeh4E9NyjYQSRMXuBnjhLwNiK5knaPQ4oKCS0w0c+4fKsiNyE7zO8iOYmE+fTYvZEnIvKOTeZZ4GGJfGktCkbvG+2u+XPe9007bPJsrptOr1BwFJyE0wB1IXhjhngQJBAO7M0KsgXDCE5J+ySCZcT4tCSNqo8OmJ34NPIw8WDWK9GraxuJK27yO+JW2wdliYFOFyLLzvy5oFuVr9I5Oo02ECQQDFtAcTlZh2DFBiwyz2q/siNRxZwvYhUEl+1UDQXwAO9QuNN4p1m0GFLTFSJBthtWDz6xcgLhW9o/xjf4sCO2yjAkBjHQ1KyY6Z8L53bea2TUASm7tjThVXyQrFFFaHz25U2go5Y8Ao3NaarjQYt9IW6WBZRis9bAXEcrXBByn6AmthAkB7TyoKwQdyuYN51ho5UFM2psGkCsHRTW5JuWDPljFHIvetgLLPX6KiGenlPbgGXUa3XC30WEofGiTVOjvNjJrZAkEAyg9RfeSj14KCflR93LaaHmrbXZDI4MR7F7FEHJx9d+vgZk1NCYV7daXBu7FrTE91DIXQTfdqYjxviq5X0tS6DQ==
    ````
    and paste it to ``Environmental variables`` input field.
    
    ![Image2](/images/2.jpg)
1. Press ``Apply`` and ``OK``. Now you are able to launch stub locally.
